package com.project.custom.checker;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class EnglishVaribaleNameChecker extends AbstractCheck {
    private static final Set<String> MY_OFFLINE_DICO = new HashSet<>(Arrays.asList(
            "my", "balance", "wallet", "color", "slim", "bifold", "trifold", "travel", "long"
    ));

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.VARIABLE_DEF};
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[0];
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    @Override
    public void visitToken(DetailAST ast) {
        var varName = Objects.requireNonNull(ast.findFirstToken(TokenTypes.IDENT)).getText();

        List<String> words = splitCamelCaseRegex(varName);

        for (String word : words) {
            if (!isEnglishWord(word)) {
                log(ast.getLineNo(), "Variable name contains non-English word: " + word);
            }
        }
    }

    public List<String> splitCamelCaseRegex(String varName) {
        List<String> words = new ArrayList<>();
        String[] wordFromVarName = varName.split("(?=[A-Z])");

        for (String p : wordFromVarName) {
            words.add(p.toLowerCase());
        }

        return words;
    }

    public boolean isEnglishWord(String word) {
        if (MY_OFFLINE_DICO.contains(word.toLowerCase())) {
            return true;
        }

        try {
            String apiURL = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
            URL urlChecker = new URL(apiURL);

            HttpURLConnection connection = (HttpURLConnection) urlChecker.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
