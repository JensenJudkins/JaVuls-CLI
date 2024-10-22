package apps.EncryptionDecryption;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecryptFilesAES {

 public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
   IllegalBlockSizeException, BadPaddingException, IOException {
  String key = "jackrutorial.com";
 
  System.out.println("File input: " + "D:\\text.txt");

  //encryptedFile
  encryptedFile(key, "D:\\text.txt", "D:\\text.enc");
  
  //decryptedFile
  decryptedFile(key, "D:\\text.enc", "D:\\text-decrypt.txt");
 }
//Encrypt Files
 public static void encryptedFile(String secretKey, String fileInputPath, String fileOutPath)
   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
   IllegalBlockSizeException, BadPaddingException {
  SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
  Cipher cipher = Cipher.getInstance("AES");
  cipher.init(Cipher.ENCRYPT_MODE, key);

  File fileInput = new File(fileInputPath);
  FileInputStream inputStream = new FileInputStream(fileInput);
  byte[] inputBytes = new byte[(int) fileInput.length()];
  inputStream.read(inputBytes);

  byte[] outputBytes = cipher.doFinal(inputBytes);

  File fileEncryptOut = new File(fileOutPath);
  FileOutputStream outputStream = new FileOutputStream(fileEncryptOut);
  outputStream.write(outputBytes);

  inputStream.close();
  outputStream.close();
  
  System.out.println("File successfully encrypted!");
  System.out.println("New File: " + fileOutPath);
 }
//Decrypt Files
 public static void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)
   throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
   IllegalBlockSizeException, BadPaddingException {
  SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
  Cipher cipher = Cipher.getInstance("AES");
  cipher.init(Cipher.DECRYPT_MODE, key);

  File fileInput = new File(fileInputPath);
  FileInputStream inputStream = new FileInputStream(fileInput);
  byte[] inputBytes = new byte[(int) fileInput.length()];
  inputStream.read(inputBytes);

  byte[] outputBytes = cipher.doFinal(inputBytes);

  File fileEncryptOut = new File(fileOutPath);
  FileOutputStream outputStream = new FileOutputStream(fileEncryptOut);
  outputStream.write(outputBytes);

  inputStream.close();
  outputStream.close();
  
  System.out.println("File successfully decrypted!");
  System.out.println("New File: " + fileOutPath);
 }
}