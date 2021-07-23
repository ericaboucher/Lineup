package beans;

import java.security.*;

public class Hash {
  
 String password;
  
  public Hash(String s) {
    
    password = s;
  }
  
  public String encrypt(String password) {
    
    try{

      MessageDigest m = MessageDigest.getInstance("MD5");
      byte[] array = m.digest(password.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; i++) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString();

    }catch (NoSuchAlgorithmException e){

      
      e.printStackTrace();

    }
    return null;
    
  }
  
}
