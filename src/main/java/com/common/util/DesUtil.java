package com.common.util;

/**
 * DES 加密/解密 仅用于消息加密，如果要对文件加密的话需要另外封装
 * 
 * @author zy.wu 2013-5-27 下午4:03:23
 */
public class DesUtil {

	private DesUtil() {
	}

	private static DesUtil desUtil = new DesUtil();

	public static DesUtil getInstance() {
		return desUtil;
	}

	/**
	 * DES加密
	 * 
	 * @param key
	 * @param srcData
	 * @return
	 */
	public String desEncode(String key, String srcData) {
		return this.strToHax(this.des(key, srcData, true, 0, null, 0));
	}

	/**
	 * DES解密
	 * 
	 * @param key
	 * @param resultData
	 * @return
	 */
	public String desDecode(String key, String resultData) {
		return this.des(key, this.haxToStr(resultData), false, 0, null, 0);
	}

	private String strToHax(String res) {
		String r = "";
		for (int i = 0; i < res.length(); i++) {
			String hex = Integer.toHexString(res.charAt(i) & 0XFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			r += hex;
		}
		return r;
	}

	private String haxToStr(String res) {
		res = res.toLowerCase();
		if (res.startsWith("0x")) {
			res = res.substring(2, res.length());
		}
		int lenght = res.length() / 2;
		char[] chars = new char[lenght];
		for (int i = 0; i < lenght; i++) {
			String tStr = res.substring(i * 2, i * 2 + 2);
			chars[i] = (char) Integer.parseInt(tStr, 16);

		}
		return new String(chars);
	}

	/**
	 * @param key
	 *            加密使用的KEY
	 * @param message
	 *            需要加密的消息
	 * @param encrypt
	 *            加解密类型 加密为true,解密为false
	 * @param mode
	 *            des加密使用的模式ECB为0(默认) CBC为1 解释： ECB就是电子代码本，就是说每个块都是独立加密的，
	 *            每个明文块所对应的密文只和该块本身和密钥有关， 在这个模式下一个密钥实际上是确定了一个明文到密文的一对一的映射
	 *            这样工作起来很象是一战以前用过的密码本（密码本上一个密文词有 一个固定的明文与之对应）
	 *            CBC大概是密文反馈吧，就是在每次加密前先把明文和前一个块的密文 取XOR，然后再加密（解密的时候反过来就行）。
	 *            这样做的好处是保证了密文的完整性，也就是说如果有一个块在传送 时丢失了（或被敌人改变了），就会导致后面所有的块无法正常解密
	 *            这个特性可以用来防范一些窃听技巧
	 * @param iv
	 *            CBC模式下使用的密钥数组
	 * @param padding
	 *            未知属性 默认为0
	 * @return
	 */
	private String des(String key, String message, boolean encrypt, int mode,
			byte[] iv, int padding) {

		int[] spfunction1 = new int[] { 0x1010400, 0, 0x10000, 0x1010404,
				0x1010004, 0x10404, 0x4, 0x10000, 0x400, 0x1010400, 0x1010404,
				0x400, 0x1000404, 0x1010004, 0x1000000, 0x4, 0x404, 0x1000400,
				0x1000400, 0x10400, 0x10400, 0x1010000, 0x1010000, 0x1000404,
				0x10004, 0x1000004, 0x1000004, 0x10004, 0, 0x404, 0x10404,
				0x1000000, 0x10000, 0x1010404, 0x4, 0x1010000, 0x1010400,
				0x1000000, 0x1000000, 0x400, 0x1010004, 0x10000, 0x10400,
				0x1000004, 0x400, 0x4, 0x1000404, 0x10404, 0x1010404, 0x10004,
				0x1010000, 0x1000404, 0x1000004, 0x404, 0x10404, 0x1010400,
				0x404, 0x1000400, 0x1000400, 0, 0x10004, 0x10400, 0, 0x1010004 };
		int[] spfunction2 = new int[] { -0x7fef7fe0, -0x7fff8000, 0x8000,
				0x108020, 0x100000, 0x20, -0x7fefffe0, -0x7fff7fe0,
				-0x7fffffe0, -0x7fef7fe0, -0x7fef8000, -0x80000000,
				-0x7fff8000, 0x100000, 0x20, -0x7fefffe0, 0x108000, 0x100020,
				-0x7fff7fe0, 0, -0x80000000, 0x8000, 0x108020, -0x7ff00000,
				0x100020, -0x7fffffe0, 0, 0x108000, 0x8020, -0x7fef8000,
				-0x7ff00000, 0x8020, 0, 0x108020, -0x7fefffe0, 0x100000,
				-0x7fff7fe0, -0x7ff00000, -0x7fef8000, 0x8000, -0x7ff00000,
				-0x7fff8000, 0x20, -0x7fef7fe0, 0x108020, 0x20, 0x8000,
				-0x80000000, 0x8020, -0x7fef8000, 0x100000, -0x7fffffe0,
				0x100020, -0x7fff7fe0, -0x7fffffe0, 0x100020, 0x108000, 0,
				-0x7fff8000, 0x8020, -0x80000000, -0x7fefffe0, -0x7fef7fe0,
				0x108000 };
		int[] spfunction3 = new int[] { 0x208, 0x8020200, 0, 0x8020008,
				0x8000200, 0, 0x20208, 0x8000200, 0x20008, 0x8000008,
				0x8000008, 0x20000, 0x8020208, 0x20008, 0x8020000, 0x208,
				0x8000000, 0x8, 0x8020200, 0x200, 0x20200, 0x8020000,
				0x8020008, 0x20208, 0x8000208, 0x20200, 0x20000, 0x8000208,
				0x8, 0x8020208, 0x200, 0x8000000, 0x8020200, 0x8000000,
				0x20008, 0x208, 0x20000, 0x8020200, 0x8000200, 0, 0x200,
				0x20008, 0x8020208, 0x8000200, 0x8000008, 0x200, 0, 0x8020008,
				0x8000208, 0x20000, 0x8000000, 0x8020208, 0x8, 0x20208,
				0x20200, 0x8000008, 0x8020000, 0x8000208, 0x208, 0x8020000,
				0x20208, 0x8, 0x8020008, 0x20200 };
		int[] spfunction4 = new int[] { 0x802001, 0x2081, 0x2081, 0x80,
				0x802080, 0x800081, 0x800001, 0x2001, 0, 0x802000, 0x802000,
				0x802081, 0x81, 0, 0x800080, 0x800001, 0x1, 0x2000, 0x800000,
				0x802001, 0x80, 0x800000, 0x2001, 0x2080, 0x800081, 0x1,
				0x2080, 0x800080, 0x2000, 0x802080, 0x802081, 0x81, 0x800080,
				0x800001, 0x802000, 0x802081, 0x81, 0, 0, 0x802000, 0x2080,
				0x800080, 0x800081, 0x1, 0x802001, 0x2081, 0x2081, 0x80,
				0x802081, 0x81, 0x1, 0x2000, 0x800001, 0x2001, 0x802080,
				0x800081, 0x2001, 0x2080, 0x800000, 0x802001, 0x80, 0x800000,
				0x2000, 0x802080 };
		int[] spfunction5 = new int[] { 0x100, 0x2080100, 0x2080000,
				0x42000100, 0x80000, 0x100, 0x40000000, 0x2080000, 0x40080100,
				0x80000, 0x2000100, 0x40080100, 0x42000100, 0x42080000,
				0x80100, 0x40000000, 0x2000000, 0x40080000, 0x40080000, 0,
				0x40000100, 0x42080100, 0x42080100, 0x2000100, 0x42080000,
				0x40000100, 0, 0x42000000, 0x2080100, 0x2000000, 0x42000000,
				0x80100, 0x80000, 0x42000100, 0x100, 0x2000000, 0x40000000,
				0x2080000, 0x42000100, 0x40080100, 0x2000100, 0x40000000,
				0x42080000, 0x2080100, 0x40080100, 0x100, 0x2000000,
				0x42080000, 0x42080100, 0x80100, 0x42000000, 0x42080100,
				0x2080000, 0, 0x40080000, 0x42000000, 0x80100, 0x2000100,
				0x40000100, 0x80000, 0, 0x40080000, 0x2080100, 0x40000100 };
		int[] spfunction6 = new int[] { 0x20000010, 0x20400000, 0x4000,
				0x20404010, 0x20400000, 0x10, 0x20404010, 0x400000, 0x20004000,
				0x404010, 0x400000, 0x20000010, 0x400010, 0x20004000,
				0x20000000, 0x4010, 0, 0x400010, 0x20004010, 0x4000, 0x404000,
				0x20004010, 0x10, 0x20400010, 0x20400010, 0, 0x404010,
				0x20404000, 0x4010, 0x404000, 0x20404000, 0x20000000,
				0x20004000, 0x10, 0x20400010, 0x404000, 0x20404010, 0x400000,
				0x4010, 0x20000010, 0x400000, 0x20004000, 0x20000000, 0x4010,
				0x20000010, 0x20404010, 0x404000, 0x20400000, 0x404010,
				0x20404000, 0, 0x20400010, 0x10, 0x4000, 0x20400000, 0x404010,
				0x4000, 0x400010, 0x20004010, 0, 0x20404000, 0x20000000,
				0x400010, 0x20004010 };
		int[] spfunction7 = new int[] { 0x200000, 0x4200002, 0x4000802, 0,
				0x800, 0x4000802, 0x200802, 0x4200800, 0x4200802, 0x200000, 0,
				0x4000002, 0x2, 0x4000000, 0x4200002, 0x802, 0x4000800,
				0x200802, 0x200002, 0x4000800, 0x4000002, 0x4200000, 0x4200800,
				0x200002, 0x4200000, 0x800, 0x802, 0x4200802, 0x200800, 0x2,
				0x4000000, 0x200800, 0x4000000, 0x200800, 0x200000, 0x4000802,
				0x4000802, 0x4200002, 0x4200002, 0x2, 0x200002, 0x4000000,
				0x4000800, 0x200000, 0x4200800, 0x802, 0x200802, 0x4200800,
				0x802, 0x4000002, 0x4200802, 0x4200000, 0x200800, 0, 0x2,
				0x4200802, 0, 0x200802, 0x4200000, 0x800, 0x4000002, 0x4000800,
				0x800, 0x200002 };
		int[] spfunction8 = new int[] { 0x10001040, 0x1000, 0x40000,
				0x10041040, 0x10000000, 0x10001040, 0x40, 0x10000000, 0x40040,
				0x10040000, 0x10041040, 0x41000, 0x10041000, 0x41040, 0x1000,
				0x40, 0x10040000, 0x10000040, 0x10001000, 0x1040, 0x41000,
				0x40040, 0x10040040, 0x10041000, 0x1040, 0, 0, 0x10040040,
				0x10000040, 0x10001000, 0x41040, 0x40000, 0x41040, 0x40000,
				0x10041000, 0x1000, 0x40, 0x10040040, 0x1000, 0x41040,
				0x10001000, 0x40, 0x10000040, 0x10040000, 0x10040040,
				0x10000000, 0x40000, 0x10001040, 0, 0x10041040, 0x40040,
				0x10000040, 0x10040000, 0x10001000, 0x10001040, 0, 0x10041040,
				0x41000, 0x41000, 0x1040, 0x1040, 0x40040, 0x10000000,
				0x10041000 };
		// 构建key十六进制
		int[] keys = buildKey(key.toCharArray());
		int m = 0, i = 0, j = 0, temp = 0, temp2 = 0, right1 = 0, right2 = 0, left = 0, right = 0, cbcleft = 0, cbcleft2 = 0, cbcright = 0, cbcright2 = 0, endloop = 0, loopinc = 0;
		int[] looping;
		int len = message.length();
		int chunk = 0;
		int iterations = keys.length == 32 ? 3 : 9;
		if (iterations == 3) {
			looping = encrypt ? new int[] { 0, 32, 2 }
					: new int[] { 30, -2, -2 };
		} else {
			looping = encrypt ? new int[] { 0, 32, 2, 62, 30, -2, 64, 96, 2 }
					: new int[] { 94, 62, -2, 32, 64, 2, 30, -2, -2 };
		}
		if (padding == 2) {
			message += "        ";
		} else if (padding == 1) {
			temp = 8 - (len % 8);
			String tempStr = new String(Character.toChars(temp));
			message += tempStr + tempStr + tempStr + tempStr + tempStr
					+ tempStr + tempStr + tempStr;
			if (temp == 8) {
				len += 8;
			}
		} else if (padding == 0) {
			message += "\0\0\0\0\0\0\0\0";
		}

		String result = "";
		String tempresult = "";

		// CBC mode
		if (mode == 1) {
			cbcleft = (iv[m++] << 24) | (iv[m++] << 16) | (iv[m++] << 8)
					| iv[m++];
			cbcright = (iv[m++] << 24) | (iv[m++] << 16) | (iv[m++] << 8)
					| iv[m++];
			m = 0;
		}
		while (m < len) {
			char[] messageBytes = message.toCharArray();

			left = (messageBytes[m++] << 24) | (messageBytes[m++] << 16)
					| (messageBytes[m++] << 8) | messageBytes[m++];
			right = (messageBytes[m++] << 24) | (messageBytes[m++] << 16)
					| (messageBytes[m++] << 8) | messageBytes[m++];
			// for Cipher Block Chaining mode, xor the message with the previous
			// result
			if (mode == 1) {
				if (encrypt) {
					left ^= cbcleft;
					right ^= cbcright;
				} else {
					cbcleft2 = cbcleft;
					cbcright2 = cbcright;
					cbcleft = left;
					cbcright = right;
				}
			}

			// first each 64 but chunk of the message must be permuted according
			// to IP
			temp = ((left >>> 4) ^ right) & 0x0f0f0f0f;
			right ^= temp;
			left ^= (temp << 4);
			temp = ((left >>> 16) ^ right) & 0x0000ffff;
			right ^= temp;
			left ^= (temp << 16);
			temp = ((right >>> 2) ^ left) & 0x33333333;
			left ^= temp;
			right ^= (temp << 2);
			temp = ((right >>> 8) ^ left) & 0x00ff00ff;
			left ^= temp;
			right ^= (temp << 8);
			temp = ((left >>> 1) ^ right) & 0x55555555;
			right ^= temp;
			left ^= (temp << 1);

			left = ((left << 1) | (left >>> 31));
			right = ((right << 1) | (right >>> 31));
			// do this either 1 or 3 times for each chunk of the message
			for (j = 0; j < iterations; j += 3) {
				endloop = looping[j + 1];
				loopinc = looping[j + 2];
				// now go through and perform the encryption or decryption
				// for
				for (i = looping[j]; i != endloop; i += loopinc) {
					// efficiency
					right1 = right ^ keys[i];
					right2 = ((right >>> 4) | (right << 28)) ^ keys[i + 1];
					// the result is attained by passing these bytes through the
					// S selection functions
					temp = left;
					left = right;
					right = temp
							^ (spfunction2[(right1 >>> 24) & 0x3f]
									| spfunction4[(right1 >>> 16) & 0x3f]
									| spfunction6[(right1 >>> 8) & 0x3f]
									| spfunction8[right1 & 0x3f]
									| spfunction1[(right2 >>> 24) & 0x3f]
									| spfunction3[(right2 >>> 16) & 0x3f]
									| spfunction5[(right2 >>> 8) & 0x3f] | spfunction7[right2 & 0x3f]);
				}
				// unreverse left and right
				temp = left;
				left = right;
				right = temp;
			} // for either 1 or 3 iterations

			// move then each one bit to the right
			left = ((left >>> 1) | (left << 31));
			right = ((right >>> 1) | (right << 31));

			// now perform IP-1, which is IP in the opposite direction
			temp = ((left >>> 1) ^ right) & 0x55555555;
			right ^= temp;
			left ^= (temp << 1);
			temp = ((right >>> 8) ^ left) & 0x00ff00ff;
			left ^= temp;
			right ^= (temp << 8);
			temp = ((right >>> 2) ^ left) & 0x33333333;
			left ^= temp;
			right ^= (temp << 2);
			temp = ((left >>> 16) ^ right) & 0x0000ffff;
			right ^= temp;
			left ^= (temp << 16);
			temp = ((left >>> 4) ^ right) & 0x0f0f0f0f;
			right ^= temp;
			left ^= (temp << 4);

			// for Cipher Block Chaining mode, xor the message with the previous
			// result
			if (mode == 1) {
				if (encrypt) {
					cbcleft = left;
					cbcright = right;
				} else {
					left ^= cbcleft2;
					right ^= cbcright2;
				}
			}
			tempresult += charToStr((left >>> 24), encrypt);
			tempresult += charToStr(((left >>> 16) & 0xff), encrypt);
			tempresult += charToStr(((left >>> 8) & 0xff), encrypt);
			tempresult += charToStr((left & 0xff), encrypt);
			tempresult += charToStr((right >>> 24), encrypt);
			tempresult += charToStr(((right >>> 16) & 0xff), encrypt);
			tempresult += charToStr(((right >>> 8) & 0xff), encrypt);
			tempresult += charToStr((right & 0xff), encrypt);
			chunk += 8;
			if (chunk == 512) {
				result += tempresult;
				tempresult = "";
				chunk = 0;
			}
		} // for every 8 characters, or 64 bits in the message
		// return the result as an array
		return result + tempresult;
	}

	private int[] buildKey(char[] key) {
		if (key.length < 8) {
			char[] tempByte = new char[8];
			for (int i = 0; i < key.length; i++) {
				tempByte[i] = key[i];
			}
			key = tempByte;
		}
		int[] pc2bytes0 = new int[] { 0, 0x4, 0x20000000, 0x20000004, 0x10000,
				0x10004, 0x20010000, 0x20010004, 0x200, 0x204, 0x20000200,
				0x20000204, 0x10200, 0x10204, 0x20010200, 0x20010204 };
		int[] pc2bytes1 = new int[] { 0, 0x1, 0x100000, 0x100001, 0x4000000,
				0x4000001, 0x4100000, 0x4100001, 0x100, 0x101, 0x100100,
				0x100101, 0x4000100, 0x4000101, 0x4100100, 0x4100101 };
		int[] pc2bytes2 = new int[] { 0, 0x8, 0x800, 0x808, 0x1000000,
				0x1000008, 0x1000800, 0x1000808, 0, 0x8, 0x800, 0x808,
				0x1000000, 0x1000008, 0x1000800, 0x1000808 };
		int[] pc2bytes3 = new int[] { 0, 0x200000, 0x8000000, 0x8200000,
				0x2000, 0x202000, 0x8002000, 0x8202000, 0x20000, 0x220000,
				0x8020000, 0x8220000, 0x22000, 0x222000, 0x8022000, 0x8222000 };
		int[] pc2bytes4 = new int[] { 0, 0x40000, 0x10, 0x40010, 0, 0x40000,
				0x10, 0x40010, 0x1000, 0x41000, 0x1010, 0x41010, 0x1000,
				0x41000, 0x1010, 0x41010 };
		int[] pc2bytes5 = new int[] { 0, 0x400, 0x20, 0x420, 0, 0x400, 0x20,
				0x420, 0x2000000, 0x2000400, 0x2000020, 0x2000420, 0x2000000,
				0x2000400, 0x2000020, 0x2000420 };
		int[] pc2bytes6 = new int[] { 0, 0x10000000, 0x80000, 0x10080000, 0x2,
				0x10000002, 0x80002, 0x10080002, 0, 0x10000000, 0x80000,
				0x10080000, 0x2, 0x10000002, 0x80002, 0x10080002 };
		int[] pc2bytes7 = new int[] { 0, 0x10000, 0x800, 0x10800, 0x20000000,
				0x20010000, 0x20000800, 0x20010800, 0x20000, 0x30000, 0x20800,
				0x30800, 0x20020000, 0x20030000, 0x20020800, 0x20030800 };
		int[] pc2bytes8 = new int[] { 0, 0x40000, 0, 0x40000, 0x2, 0x40002,
				0x2, 0x40002, 0x2000000, 0x2040000, 0x2000000, 0x2040000,
				0x2000002, 0x2040002, 0x2000002, 0x2040002 };
		int[] pc2bytes9 = new int[] { 0, 0x10000000, 0x8, 0x10000008, 0,
				0x10000000, 0x8, 0x10000008, 0x400, 0x10000400, 0x408,
				0x10000408, 0x400, 0x10000400, 0x408, 0x10000408 };
		int[] pc2bytes10 = new int[] { 0, 0x20, 0, 0x20, 0x100000, 0x100020,
				0x100000, 0x100020, 0x2000, 0x2020, 0x2000, 0x2020, 0x102000,
				0x102020, 0x102000, 0x102020 };
		int[] pc2bytes11 = new int[] { 0, 0x1000000, 0x200, 0x1000200,
				0x200000, 0x1200000, 0x200200, 0x1200200, 0x4000000, 0x5000000,
				0x4000200, 0x5000200, 0x4200000, 0x5200000, 0x4200200,
				0x5200200 };
		int[] pc2bytes12 = new int[] { 0, 0x1000, 0x8000000, 0x8001000,
				0x80000, 0x81000, 0x8080000, 0x8081000, 0x10, 0x1010,
				0x8000010, 0x8001010, 0x80010, 0x81010, 0x8080010, 0x8081010 };
		int[] pc2bytes13 = new int[] { 0, 0x4, 0x100, 0x104, 0, 0x4, 0x100,
				0x104, 0x1, 0x5, 0x101, 0x105, 0x1, 0x5, 0x101, 0x105 };
		int iterations = key.length >= 24 ? 3 : 1;
		int[] keys = new int[32 * iterations];
		boolean[] shifts = new boolean[] { false, false, true, true, true,
				true, true, true, false, true, true, true, true, true, true,
				false };
		int lefttemp, righttemp, m = 0, n = 0, temp;
		for (int j = 0; j < iterations; j++) {
			int left = (key[m++] << 24) | (key[m++] << 16) | (key[m++] << 8)
					| key[m++];
			int right = (key[m++] << 24) | (key[m++] << 16) | (key[m++] << 8)
					| key[m++];

			temp = ((left >>> 4) ^ right) & 0x0f0f0f0f;
			right ^= temp;
			left ^= (temp << 4);
			temp = ((right >>> -16) ^ left) & 0x0000ffff;
			left ^= temp;
			right ^= (temp << -16);
			temp = ((left >>> 2) ^ right) & 0x33333333;
			right ^= temp;
			left ^= (temp << 2);
			temp = ((right >>> -16) ^ left) & 0x0000ffff;
			left ^= temp;
			right ^= (temp << -16);
			temp = ((left >>> 1) ^ right) & 0x55555555;
			right ^= temp;
			left ^= (temp << 1);
			temp = ((right >>> 8) ^ left) & 0x00ff00ff;
			left ^= temp;
			right ^= (temp << 8);
			temp = ((left >>> 1) ^ right) & 0x55555555;
			right ^= temp;
			left ^= (temp << 1);

			// the right side needs to be shifted and to get the last four bits
			// of
			// the left side
			temp = (left << 8) | ((right >>> 20) & 0x000000f0);
			// left needs to be put upside down
			left = (right << 24) | ((right << 8) & 0xff0000)
					| ((right >>> 8) & 0xff00) | ((right >>> 24) & 0xf0);
			right = temp;

			// now go through and perform these shifts on the left and right
			// keys
			for (int i = 0; i < shifts.length; i++) {
				// shift the keys either one or two bits to the left
				if (shifts[i]) {
					left = (left << 2) | (left >>> 26);
					right = (right << 2) | (right >>> 26);
				} else {
					left = (left << 1) | (left >>> 27);
					right = (right << 1) | (right >>> 27);
				}
				left &= -0xf;
				right &= -0xf;
				lefttemp = pc2bytes0[left >>> 28]
						| pc2bytes1[(left >>> 24) & 0xf]
						| pc2bytes2[(left >>> 20) & 0xf]
						| pc2bytes3[(left >>> 16) & 0xf]
						| pc2bytes4[(left >>> 12) & 0xf]
						| pc2bytes5[(left >>> 8) & 0xf]
						| pc2bytes6[(left >>> 4) & 0xf];
				righttemp = pc2bytes7[right >>> 28]
						| pc2bytes8[(right >>> 24) & 0xf]
						| pc2bytes9[(right >>> 20) & 0xf]
						| pc2bytes10[(right >>> 16) & 0xf]
						| pc2bytes11[(right >>> 12) & 0xf]
						| pc2bytes12[(right >>> 8) & 0xf]
						| pc2bytes13[(right >>> 4) & 0xf];
				temp = ((righttemp >>> 16) ^ lefttemp) & 0x0000ffff;
				keys[n++] = lefttemp ^ temp;
				keys[n++] = righttemp ^ (temp << 16);
			}
		} // for each iterations
		// return the keys we've created
		return keys;
	}

	private String charToStr(int charNum, boolean encrypt) {
		if (charNum == 0 && !encrypt) {
			return "";
		} else {
			return String.valueOf((char) charNum);
		}

	}
	
	public static void main(String[] args) {

		//13474672343
		System.out.println(desUtil.desDecode("11872", "65dd608bdb54f81d637ba5a90e8d4a4a"));
		System.out.println(desUtil.desDecode("12857", "84180798968a0b28"));

		//lichuanjie
		System.out.println(desUtil.desDecode("3", "b66e1d42f6ac7157981ae37378fe19dc"));
		System.out.println(desUtil.desDecode("17987", "5e00dfeedf0f7aa2"));

		//123456
		System.out.println(desUtil.desDecode("18930", "2a50f5e1ea6e0010"));

		//123456
		System.out.println(desUtil.desDecode("20200", "4711e6eb52d244c3"));
	}
}
