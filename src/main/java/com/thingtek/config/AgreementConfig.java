package com.thingtek.config;


import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public@Data
class AgreementConfig {

    private int orderOff;

    private int orderLength;

    private Map<Integer, String> c2sOrderClassMap;

    private Map<Integer, Integer> orderBackMap;

    private int agreementVersionOff;

    private byte agreementVersion;

    private int obligateOff;

    private int obligateLength;

    private int stateOff;

    private byte[] stateCodes;

    private int dataOff;

    private int totalLength;

    private byte head;

    private byte tail;

    private Map<Byte, Byte> encripts;

    private Map<Byte, Byte> decripts;

    private int crcStartOff;

    private int crcEndOff;

    private byte criptcode;

    private int IdAuthentySnidLength;

    private int IdAuthentySnidOff;

    private int IdAuthentyPwdLength;

    private int IdAuthentyPwdOff;

    private int IdAuthentySoftLength;

    private int IdAuthentySoftOff;

    private int IdAuthentyHardLength;

    private int IdAuthentyHardOff;

    private Map<Boolean, Byte> IdAuthentyStates;

    private int dataLength;

    private int taskLength;

    private int dayLength;

    private int startDayOff;

    private int endDayOff;

    private int uploadDataOff;

    private int uploadTaskOff;

    private int uploadFirstDayOff;

    private int historyNumOff;

    private int historyDataStartOff;

    private int historyOneDataLength;

    private Map<Byte, Boolean> historyStateFlags;

    private Map<Boolean, Byte> historyFlagStates;

    private Map<Byte, Byte> s2ghistoryStates;


    public boolean checkAgreement(byte[] datas) {
        return datas != null && datas.length > 1 && datas[0] == head && datas[datas.length - 1] == tail;
    }


    private int ringNum;
    private int ringOff;
    private int ringOneLength;
    private int batlvOff;
    private int batlvLength;
    private int dayOff;
    private int deepOff;
    private int deepLength;
    private int ringnumOff;
    private int ringnumLength;
    private int periodOff;
    private int periodLength;
    private int speedOff;
    private int speedLength;
    private int tcpAddrOff;
    private int tcpPortOff;
    private int ftpAddrOff;
    private int ftpPortOff;
    private int addrLength;
    private int portLength;


    public synchronized byte[] decript(byte[] bytes) {
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == criptcode) {
                list.add(decripts.get(bytes[i + 1]));
            } else {
                list.add(bytes[i]);
            }
        }
        byte[] ret = new byte[list.size()];
        int i = 0;
        for (byte data : list) {
            ret[i] = data;
            i++;
        }
        return ret;
    }

    public synchronized byte[] encript(byte[] bytes) {
        List<Byte> list = new ArrayList<>();
        list.add(head);
        for (int i = 1; i < bytes.length - 1; i++) {
            if (encripts.containsKey(bytes[i])) {
                list.add(criptcode);
                list.add(encripts.get(bytes[i]));
            } else {
                list.add(bytes[i]);
            }
        }
        list.add(tail);
        byte[] ret = new byte[list.size()];
        int i = 0;
        for (byte data : list) {
            ret[i] = data;
            i++;
        }
        return ret;
    }

    public synchronized boolean checkCRC16_X(byte[] bytes) {
        byte[] crc = getCrcBytes(bytes, crcStartOff, crcEndOff - crcStartOff < 0 ? crcEndOff - crcStartOff + bytes.length : crcEndOff - crcStartOff);
        System.out.println(Arrays.toString(crc));
        byte[] dataCRC = new byte[crc.length];
        System.arraycopy(bytes, bytes.length + crcEndOff, dataCRC, 0, dataCRC.length);
        System.out.println(Arrays.toString(dataCRC));
        return Arrays.equals(crc, dataCRC);
    }

    public synchronized byte[] getCrcBytes(byte[] bytes, int off, int length) {
        int crc = getCrc(bytes, off, length);
        byte[] checkcode = new byte[2];
        checkcode[0] = (byte) (crc & 0xff);
        checkcode[1] = (byte) ((crc >> 8) & 0xff);
        return checkcode;
    }

    private synchronized int getCrc(byte[] bytes, int off, int len) {
        int crc = 0x00;
        int polynomial = 0x1021;
        for (int index = off; index < off + len; index++) {
            byte b = bytes[index];
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit)
                    crc ^= polynomial;
            }
        }
        crc &= 0xffff;
        return crc;
    }

}
