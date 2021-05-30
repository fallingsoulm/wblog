package com.wblog.info.utils.charset;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


/**
 * <Detect encoding .>
 * Copyright (C) <2009>  <Fluck,ACC http://androidos.cc/dev>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * CharsetUtils.java<br>
 * 自动获取文件的编码
 *
 * @author Billows.Van
 * @version 1.0
 * @since Create on 2010-01-27 11:19:00
 */
public class CharsetUtils {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("参数错误：");
            System.out.println("使用方法：");
            System.out.println("java CharsetUtils  要检测的文件");
        }
        String file = args[0];
        String encode = getJavaEncode(file);
        System.out.println(encode);
    }

    /**
     * 得到文件的编码
     *
     * @param filePath 文件路径
     * @return 文件的编码
     */
    public static String getJavaEncode(String filePath) {
        BytesEncodingDetect s = new BytesEncodingDetect();
        String fileCode = BytesEncodingDetect.javaname[s.detectEncoding(new File(filePath))];
        return fileCode;
    }

    /**
     * 得到文件的编码
     *
     * @param file 文件路径
     * @return 文件的编码
     */
    public static String getJavaEncode(File file) {

        BytesEncodingDetect s = new BytesEncodingDetect();
        String fileCode = BytesEncodingDetect.javaname[s.detectEncoding(file)];
        return fileCode;
    }

    public static Charset getCharset(File file) {
        String[] charsetsToBeTested = {"GB2312", "GBK", "UTF-8"};
        Charset charset = detectCharset(file, charsetsToBeTested);
        return charset;
    }

    public static void readFile(String file, String code) {

        BufferedReader fr;
        try {
            String myCode = code != null && !"".equals(code) ? code : "UTF8";
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), myCode);

            fr = new BufferedReader(read);
            String line = null;
            int flag = 1;
            // 读取每一行，如果结束了，line会为空
            while ((line = fr.readLine()) != null && line.trim().length() > 0) {
                if (flag == 1) {
                    line = line.substring(1);//去掉文件头
                    flag++;
                }
                // 每一行创建一个Student对象，并存入数组中
                System.out.println(line);
            }
            fr.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static Charset detectCharset(File f, String[] charsets) {

        Charset charset = null;

        for (String charsetName : charsets) {
            charset = detectCharset(f, Charset.forName(charsetName));
            if (charset != null) {
                break;
            }
        }

        return charset;
    }

    private static Charset detectCharset(File f, Charset charset) {
        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(f));

            CharsetDecoder decoder = charset.newDecoder();
            decoder.reset();

            byte[] buffer = new byte[512];
            boolean identified = false;
            while ((input.read(buffer) != -1) && (!identified)) {
                identified = identify(buffer, decoder);
            }

            input.close();

            if (identified) {
                return charset;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    private static boolean identify(byte[] bytes, CharsetDecoder decoder) {
        try {
            decoder.decode(ByteBuffer.wrap(bytes));
        } catch (CharacterCodingException e) {
            return false;
        }
        return true;
    }
}


