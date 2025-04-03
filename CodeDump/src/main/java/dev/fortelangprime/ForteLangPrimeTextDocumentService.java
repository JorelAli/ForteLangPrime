package dev.fortelangprime;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.TextDocumentService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ForteLangPrimeTextDocumentService implements TextDocumentService {
	@Override
	public void didOpen(DidOpenTextDocumentParams didOpenTextDocumentParams) {

	}

	@Override
	public void didChange(DidChangeTextDocumentParams didChangeTextDocumentParams) {

	}

	@Override
	public void didClose(DidCloseTextDocumentParams didCloseTextDocumentParams) {

	}

	@Override
	public void didSave(DidSaveTextDocumentParams didSaveTextDocumentParams) {

	}

	@Override
	public CompletableFuture<SemanticTokens> semanticTokensFull(SemanticTokensParams params) {
		// Here’s where we use the ANTLR parser
		String uri = params.getTextDocument().getUri();
		String code = openFiles.get(uri); // assume you keep files in memory
		List<SemanticToken> tokens = ForteSemanticHighlighter.tokenize(code);
		return CompletableFuture.completedFuture(encodeTokens(tokens));
	}

	// You’ll need to implement this properly
	private SemanticTokens encodeTokens(List<SemanticToken> tokens) {
		List<Integer> data = new ArrayList<>();
		int lastLine = 0;
		int lastChar = 0;
		for (SemanticToken token : tokens) {
			int deltaLine = token.line - lastLine;
			int deltaStart = deltaLine == 0 ? token.startChar - lastChar : token.startChar;
			data.add(deltaLine);
			data.add(deltaStart);
			data.add(token.length);
			data.add(token.tokenType);
			data.add(token.tokenModifiers);
			lastLine = token.line;
			lastChar = token.startChar;
		}
		return new SemanticTokens(data);
	}
}