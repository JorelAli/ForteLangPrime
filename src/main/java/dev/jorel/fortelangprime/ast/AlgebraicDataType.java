package dev.jorel.fortelangprime.ast;

import java.util.List;

import org.objectweb.asm.ClassWriter;

import dev.jorel.fortelangprime.ast.CodeableClass;
import dev.jorel.fortelangprime.compiler.UniversalContext;

public class AlgebraicDataType implements CodeableClass {

	private String name;
	private List<String> values;

	public AlgebraicDataType(String name, List<String> values) {
		this.name = name;
		this.values = values;
	}

	@Override
	public void emit(ClassWriter classWriter, UniversalContext context) {
		// TODO Auto-generated method stub
		
	}


}
