package org.example;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;

public class TokenizerMain {
    static void main() {
        //https://jtokkit.knuddels.de/docs/getting-started/usage
        //https://huggingface.co/sentence-transformers/all-MiniLM-L6-v2/tree/main
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding tokenizer = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_001);

        var text = "Hello, do you like tea? someunknowplace";

        // Encode
        IntArrayList tokenIds = tokenizer.encode(text);
        System.out.println("Total tokens: " + tokenIds.size());
        System.out.println(tokenIds);

        // Decode
        String textDecoded = tokenizer.decode(tokenIds);
        System.out.println("Decoded: " + textDecoded);

        tokenizer.
    }
}
