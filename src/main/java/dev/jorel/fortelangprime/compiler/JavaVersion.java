package dev.jorel.fortelangprime.compiler;

import org.objectweb.asm.Opcodes;

public enum JavaVersion implements Opcodes {

	V_1(V1_1), V_2(V1_2), V_3(V1_3), V_4(V1_4), V_5(V1_5), V_6(V1_6), V_7(V1_7), V_8(V1_8), V_9(V9), V_10(V10),
	V_11(V11), V_12(V12);//, V_13(V13), V_14(V14);

	private int version;

	JavaVersion(int version) {
		this.version = version;
	}
	
	public int getVersion() {
		return this.version;
	}
}