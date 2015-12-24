package com.translationdata;

import java.io.File;
import java.io.FileFilter;

import org.junit.Test;

public class ListJavaFiles {
	public void listFiles(String path) {
		final File dir = new File(path);
		
		final FileFilter fileFilter = pathname -> pathname.getPath().endsWith(".java");
		final File[] files = dir.listFiles(fileFilter);
		
		for (File file : files)
			System.out.println("File name is " + file.getName());
			
	}

	@Test
	public void fileFilterProto() {
		listFiles("E:/dev/sts-workspace/ProjectEuler/src/main/java/com/translationdata/p000");
	}
}

