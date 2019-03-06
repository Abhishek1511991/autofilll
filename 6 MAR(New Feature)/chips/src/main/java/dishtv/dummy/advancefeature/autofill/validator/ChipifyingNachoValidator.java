package dishtv.dummy.advancefeature.autofill.validator;

import android.text.SpannableStringBuilder;
import android.util.Pair;

import java.util.List;

import androidx.annotation.NonNull;
import dishtv.dummy.advancefeature.autofill.tokenizer.ChipTokenizer;

public class ChipifyingNachoValidator implements NachoValidator {

    @Override
    public boolean isValid(@NonNull ChipTokenizer chipTokenizer, CharSequence text) {

        // The text is considered valid if there are no unterminated tokens (everything is a chip)
        List<Pair<Integer, Integer>> unterminatedTokens = chipTokenizer.findAllTokens(text);
        return unterminatedTokens.isEmpty();
    }

    @Override
    public CharSequence fixText(@NonNull ChipTokenizer chipTokenizer, CharSequence invalidText) {
        SpannableStringBuilder newText = new SpannableStringBuilder(invalidText);
        chipTokenizer.terminateAllTokens(newText);
        return newText;
    }
}
