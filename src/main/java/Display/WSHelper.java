/*
 * WSHelper.java
 *
 * Created on 11 September 2010, 20:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Display;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Alex Billingsley
 */
public class WSHelper {
    
	/* http://www.asgarli.net/2011/03/replacing-sunmiscbase64encoder-and.html */
    
    static public String OToS(Object obj) {
        
        long start=System.currentTimeMillis();
        String out = null;
        if (obj != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                
                out = Base64.encodeBase64String(baos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        
        long end=System.currentTimeMillis();
        System.out.println("Encode:"+(end-start));
        return out;
    }
    
    static public Object SToO(String str) {
        long start=System.currentTimeMillis();
        Object out = null;
        if (str != null) {
            try {
                ByteArrayInputStream bios = new
                        ByteArrayInputStream(Base64.decodeBase64(str));
                ObjectInputStream ois = new ObjectInputStream(bios);
                out = ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("Decode:"+(end-start));
        return out;
    }
}
