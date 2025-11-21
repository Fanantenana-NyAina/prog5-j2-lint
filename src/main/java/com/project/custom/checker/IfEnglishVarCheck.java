package com.project.custom.checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.io.InputStream;
import java.util.*;

public class IfEnglishVarCheck extends AbstractCheck {
    private static final Set<String> englishWords = loadEnglishWords();

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.VARIABLE_DEF};
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }

    @Override
    public void visitToken(DetailAST ast) {
        var varName = Objects.requireNonNull(ast.findFirstToken(TokenTypes.IDENT)).getText();

        List<String> words = splitCamelCaseRegex(varName);

        for (String word : words) {
            if (!isEnglishWord(word)) {
                log(ast.getLineNo(), "non-English word: " + word);
            }
        }
    }

    public List<String> splitCamelCaseRegex(String varName) {
        List<String> words = new ArrayList<>();
        String[] wordFromVarName = varName.split("(?=[A-Z])");

        for (String word : wordFromVarName) {
            words.add(word.toLowerCase());
        }

        return words;
    }

    private static boolean isEnglishWord(String word) {
        return englishWords.contains(word.toLowerCase());
    }

    private static Set<String> loadEnglishWords() {
        try (InputStream is = IfEnglishVarCheck.class.getResourceAsStream("/english_words.json")) {
            ObjectMapper mapper = new ObjectMapper();
            String[] wordsArray = mapper.readValue(is, String[].class);
            return new HashSet<>(Arrays.asList(wordsArray));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load English words JSON", e);
        }
    }
}
