package com.github.jvmgo.clazz;

import java.math.BigInteger;

public class ClassReader {
	private byte[] classData;
	private int currentIndex;
	
	public ClassReader(byte[] aClassData) {
		this.classData = aClassData;
		this.currentIndex = 0;
	}
	
	
	public int readUint8() {
		int result = -1;
		String hex8 = this.getByteHexString(8);
		result = new BigInteger(hex8, 16).intValue();
		return result;
	}
	
	public int readUint16() {
		int result = -1;
		String hex16 = this.getByteHexString(16);
		result = new BigInteger(hex16, 16).intValue();
		
		return result;
	}
	
	public long readUint32() {
		long result = -1;
		String hex32 = this.getByteHexString(32);
		result = new BigInteger(hex32, 16).longValue();
		return result;
	}
	
	public byte[] readByte(int length) {
		byte[] result = new byte[length];
		
		for(int i = 0 ;i< length;i++) {
			result[i] =this.getByte();
		}
		return result;
	}
	
	
	private String getByteHexString(int byteSize) {
		StringBuffer hexString = new StringBuffer();
		
		for(int i = 0; i < byteSize/8 ; i++) {
			hexString.append(String.format("%02x",this.getByte()));
		}
		return hexString.toString();
	}
	
	private byte getByte() {
		byte b = this.classData[currentIndex];
		currentIndex ++;
		return b;
	}
	
	
	public boolean isEnd() {
		return this.currentIndex >=this.classData.length;
	}
	
	public void reset() {
		this.currentIndex = 0;
	}
}