package com.goscamsdkdemo.add.bell;

import java.io.UnsupportedEncodingException;

import voice.StringEncoder;

public class UtfEncoder implements StringEncoder {
    public UtfEncoder() {
    }

    @Override
    public byte[] string2Bytes(String _s) {
        byte[] bytes = null;

        try {
            bytes = _s.getBytes("utf-8");
        } catch (UnsupportedEncodingException var4) {
            ;
        }

        if (bytes == null) {
            bytes = _s.getBytes();
        }

        return bytes;
    }
    @Override
    public String bytes2String(byte[] _bytes, int _off, int _len) {
        String s = null;

        try {
            s = new String(_bytes, _off, _len, "utf-8");
        } catch (UnsupportedEncodingException var6) {
            ;
        }

        if (s == null) {
            s = new String(_bytes, _off, _len);
        }

        return s;
    }
}
