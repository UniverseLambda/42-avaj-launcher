package com.clsaad.avaj;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Output implements AutoCloseable {
	public static PrintStream out = null;

	public Output() throws IOException {
		var path = Paths.get("simulation.txt");

		out = new PrintStream(
				Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
	}

	@Override
	public void close() throws Exception {
		out.close();
	}
}
