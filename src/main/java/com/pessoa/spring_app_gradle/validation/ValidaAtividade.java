package com.pessoa.spring_app_gradle.validation;

public final class ValidaAtividade {
	public static boolean validaStr(String str) {
		return (!str.isEmpty() && str.length()>3);
	}
}
