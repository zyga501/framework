package framework.utils;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

public class Zip {
    public static String gZip(String string) {
        if (StringUtils.convertNullableString(string).isEmpty()) {
            return string;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = null;
        try {
            gzipOutputStream = new GZIPOutputStream(out);
            gzipOutputStream.write(string.getBytes());
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        finally {
            if (gzipOutputStream != null) {
                try {
                    gzipOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new BASE64Encoder().encode(out.toByteArray());
    }

    public static String gUnZip(String string) {
        if (StringUtils.convertNullableString(string).isEmpty()) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(string);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                }
                catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

    public static final String zip(String string) {
        if (StringUtils.convertNullableString(string).isEmpty())
            return null;
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(string.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr = new sun.misc.BASE64Encoder().encodeBuffer(compressed);
        }
        catch (IOException e) {
            compressed = null;
        }
        finally {
            if (zout != null) {
                try {
                    zout.close();
                }
                catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException e) {
                }
            }
        }
        return compressedStr;
    }

    public static final String unZip(String string) {
        if (StringUtils.convertNullableString(string).isEmpty()) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(string);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        }
        catch (IOException e) {
            decompressed = null;
        }
        finally {
            if (zin != null) {
                try {
                    zin.close();
                }
                catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException e) {
                }
            }
        }
        return decompressed;
    }
}
