package liu.jvmclassloader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader{
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.findClass("Hello");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod("hello");
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] srtbyte = new byte[0];
        try {
            srtbyte = this.getContent("src/main/resources/Hello.xlass");
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] classBytes = decode(srtbyte);
        return defineClass(name,classBytes,0,classBytes.length);
    }

    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }

    // decode
    private static byte[] decode(byte[] byteArr) {
        byte[] targetArr = new byte[byteArr.length];
        for (int i = 0; i < byteArr.length; i++) {
            targetArr[i] = (byte) (255 - byteArr[i]);
        }
        return targetArr;
    }

}
