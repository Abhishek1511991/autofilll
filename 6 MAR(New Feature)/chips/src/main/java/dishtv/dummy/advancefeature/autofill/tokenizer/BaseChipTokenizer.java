package dishtv.dummy.advancefeature.autofill.tokenizer;

import android.text.Editable;
import android.text.Spanned;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dishtv.dummy.advancefeature.autofill.ChipConfiguration;
import dishtv.dummy.advancefeature.autofill.chip.Chip;

public abstract class BaseChipTokenizer implements ChipTokenizer {

    @Override
    public void applyConfiguration(Editable text, ChipConfiguration chipConfiguration) {
        // Do nothing
    }

    @Override
    public int findTokenStart(CharSequence charSequence, int i) {
        // Do nothing
        return 0;
    }

    @Override
    public int findTokenEnd(CharSequence charSequence, int i) {
        // Do nothing
        return 0;
    }

    @NonNull
    @Override
    public List<Pair<Integer, Integer>> findAllTokens(CharSequence text) {
        // Do nothing
        return new ArrayList<>();
    }

    @Override
    public CharSequence terminateToken(CharSequence charSequence, @Nullable Object data) {
        // Do nothing
        return charSequence;
    }

    @Override
    public void terminateAllTokens(Editable text) {
        // Do nothing
    }

    @Override
    public int findChipStart(Chip chip, Spanned text) {
        // Do nothing
        return 0;
    }

    @Override
    public int findChipEnd(Chip chip, Spanned text) {
        // Do nothing
        return 0;
    }

    @NonNull
    @Override
    public Chip[] findAllChips(int start, int end, Spanned text) {
        return new Chip[]{};
    }

    @Override
    public void revertChipToToken(Chip chip, Editable text) {
        // Do nothing
    }

    @Override
    public void deleteChip(Chip chip, Editable text) {
        // Do nothing
    }

    @Override
    public void deleteChipAndPadding(Chip chip, Editable text) {
        // Do nothing
    }
}
