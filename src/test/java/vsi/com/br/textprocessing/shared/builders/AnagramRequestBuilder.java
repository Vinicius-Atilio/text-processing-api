package vsi.com.br.textprocessing.shared.builders;

import vsi.com.br.textprocessing.shared.request.AnagramRequest;

import java.util.List;

public class AnagramRequestBuilder {
    private List<String> letters;


    public AnagramRequestBuilder() {}

    public AnagramRequestBuilder withLetters(List<String> letters) {
        this.letters = letters;
        return this;
    }

    public AnagramRequest build()  {
        return new AnagramRequest(this.letters);
    }
}
