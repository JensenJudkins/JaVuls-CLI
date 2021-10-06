package org.shortpasta.icmp2;

import org.shortpasta.icmp2.platform.NativeBridge;
import org.shortpasta.icmp2.platform.windows.WindowsNativeBridge;
import org.shortpasta.icmp2.platform.java.JavaNativeBridge;
import org.shortpasta.icmp2.platform.linux.LinuxNativeBridge;

import com.sun.jna.Platform;

/**
 * ShortPasta Foundation
 * http://www.shortpasta.org
 * Copyright 2009 and beyond, Sal Ingrilli at the ShortPasta Software Foundation
 * <p/>
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation as long as:
 * 1. You credit the original author somewhere within your product or website
 * 2. The credit is easily reachable and not burried deep
 * 3. Your end-user can easily see it
 * 4. You register your name (optional) and company/group/org name (required)
 * at http://www.shortpasta.org
 * 5. You do all of the above within 4 weeks of integrating this software
 * 6. You contribute feedback, fixes, and requests for features
 * <p/>
 * If/when you derive a commercial gain from using this software
 * please donate at http://www.shortpasta.org
 * <p/>
 * If prefer or require, contact the author specified above to:
 * 1. Release you from the above requirements
 * 2. Acquire a commercial license
 * 3. Purchase a support contract
 * 4. Request a different license
 * 5. Anything else
 * <p/>
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, similarly
 * to how this is described in the GNU Lesser General Public License.
 * <p/>
 * User: Sal Ingrilli
 * Date: May 23, 2014
 * Time: 6:11:59 PM
 */
public class IcmpPingUtil {
  
  // my attributes
  private static NativeBridge nativeBridge;
  
  // my attributes
  public static void setNativeBridge (final NativeBridge nativeBridge) { IcmpPingUtil.nativeBridge = nativeBridge; }
  public static NativeBridge getNativeBridge () { return nativeBridge; }

  /**
   * Uniformly initializes the Icmp subsystem
   * This is in an explicit method and NOT a static initializer so that the caller gets the full stack trace
   */
  public static void initialize () {
    
    // already initialized?
    if (nativeBridge != null) {
      return;
    }

    // support concurrency
    synchronized (IcmpPingUtil.class) {
      
      // already initialized?
      if (nativeBridge != null) {
        return;
      }

      // initialize
      // platform-specific processing
      nativeBridge =
        Platform.isWindows () ? new WindowsNativeBridge () :
        Platform.isLinux () ? new LinuxNativeBridge () :
        Platform.isSolaris () ? new LinuxNativeBridge () :
        new JavaNativeBridge ();
      nativeBridge.initialize ();
    }
  }
  
  /**
   * Uniformly destroys the Icmp subsystem
   */
  public static void destroy () {
    
    // delegate
    if (nativeBridge != null) {
      nativeBridge.destroy ();
      nativeBridge = null;
    }
  }

  /**
   * Returns a new IcmpEchoRequest filled with all default values
   * These values can change over time
   * @return IcmpEchoRequest
   */
  public static IcmpPingRequest createIcmpPingRequest () {

    final IcmpPingRequest request = new IcmpPingRequest ();
    request.setHost ("localhost");
    request.setPacketSize (32);
    request.setTimeout (5000);
    request.setTtl (255);

    // done
    return request;
  }
  
  /**
   * Returns a new IcmpPingResponse filled with all default values to indicate a timeout
   * These values can change over time
   * @param duration
   * @return IcmpEchoRequest
   */
  public static IcmpPingResponse createTimeoutIcmpPingResponse (final long duration) {
    
    // objectify
    final IcmpPingResponse response = new IcmpPingResponse ();
    response.setErrorMessage ("Timeout reached after " + duration + " msecs");
    response.setSuccessFlag (false);
    response.setTimeoutFlag (true);

    // done
    return response;
  }

  /**
   * Executes the given icmp ECHO request
   * This call blocks until a response is received or a timeout is reached
   * 
   * The jna implementation adapted from:
   *   http://hp.vector.co.jp/authors/VA033015/jnasamples.html
   * 
   * @param request
   * @return IcmpEchoResponse
   */
  public static IcmpPingResponse executePingRequest (final IcmpPingRequest request) {

    // jit-initialize
    initialize ();
    
    // assert preconditions
    {
      final String host = request.getHost ();
      if (host == null) {
        throw new RuntimeException ("host must be specified");
      }
    }
    
    // assert preconditions
    {
      final int packetSize = request.getPacketSize ();
      if (packetSize == 0) {
        throw new RuntimeException ("packetSize must be > 0: " + packetSize);
      }
    }
    
    // delegate
    final IcmpPingResponse response = nativeBridge.executePingRequest (request);
    
    // postconditions: rtt should not be a crazy value
    final int rtt = response.getRtt ();
    if (rtt == Integer.MAX_VALUE) {
      throw new RuntimeException ("rtt should not be MAX_VALUE: " + rtt);
    }

    // postconditions: rtt should not be > timeout
    final long timeout = request.getTimeout ();
    if (timeout > 0 && rtt > timeout) {
      throw new RuntimeException ("rtt should not be > timeout: " + rtt + " / " + timeout);
    }
    
    // done
    return response;
  }

  /**
   * Executes the given icmp ECHO request
   * See executeIcmpPingRequest (IcmpPingRequest)
   * @param host
   * @param packetSize
   * @param timeout
   * @return IcmpPingResponse
   */
  public static IcmpPingResponse executePingRequest (
    final String host,
    final int packetSize,
    final long timeout) {

    // delegate
    final IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest ();
    request.setHost (host);
    request.setPacketSize (packetSize);
    request.setTimeout (timeout);
    return executePingRequest (request);
  }
  
  /**
   * Uniformly formats the given response into a presentable message
   * @param response
   * @return String
   */
  public static String formatResponse (final IcmpPingResponse response) {

    // request
    final boolean successFlag = response.getSuccessFlag ();
    final String address = response.getHost ();
    final String message = response.getErrorMessage ();
    final int size = response.getSize ();
    final int rtt = response.getRtt ();
    final int ttl = response.getTtl ();

    // format windows style:
    // ping google.com
    //   Reply from 74.125.224.46: bytes=32 time=33ms TTL=56
    // ping google.com.asd
    //   Ping request could not find host google.com.asd. Please check the name and try again.
    return successFlag ?
      "Reply from " + address + ": bytes=" + size + " time=" + rtt + "ms TTL=" + ttl :
      "Error: " + message;
  }
}