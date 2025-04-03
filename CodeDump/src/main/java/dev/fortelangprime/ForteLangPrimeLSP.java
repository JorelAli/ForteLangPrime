package dev.fortelangprime;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ForteLangPrimeLSP implements LanguageServer, LanguageClientAware {

	private LanguageClient client;

	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams initializeParams) {
		InitializeResult result = new InitializeResult(new ServerCapabilities());

		SemanticTokensLegend legend = new SemanticTokensLegend();
		legend.setTokenTypes(List.of("keyword", "variable", "type", "operator", "string", "number", "function"));
		legend.setTokenModifiers(List.of("declaration"));

		SemanticTokensWithRegistrationOptions semanticOptions = new SemanticTokensWithRegistrationOptions();
		semanticOptions.setFull(true);
		semanticOptions.setLegend(legend);

		ServerCapabilities capabilities = result.getCapabilities();
		capabilities.setSemanticTokensProvider(semanticOptions);

		result.setCapabilities(capabilities);
		return CompletableFuture.completedFuture(result);
	}

	@Override
	public CompletableFuture<Object> shutdown() {
		return null;
	}

	@Override
	public void exit() {

	}

	@Override
	public TextDocumentService getTextDocumentService() {

	}

	@Override
	public WorkspaceService getWorkspaceService() {
		return null;
	}

	@Override
	public void connect(LanguageClient languageClient) {
		this.client = languageClient;
	}
}
