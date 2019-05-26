package querqy.lucene.contrib.rewrite;

import org.apache.lucene.index.IndexReader;
import org.junit.Assert;
import org.junit.Test;
import querqy.trie.TrieMap;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class WordBreakCompoundRewriterFactoryTest {

    @Test
    public void testThatTriggerWordsAreTurnedToLowerCaseForFlagLowerCaseInputTrue() {
        final WordBreakCompoundRewriterFactory factory = new WordBreakCompoundRewriterFactory("w1", () -> null,
                "field1", true, 1, 2, 1, Arrays.asList("Word1", "word2"), false, 2, false);

        final TrieMap<Boolean> triggerWords = factory.getReverseCompoundTriggerWords();
        Assert.assertTrue(triggerWords.get("word1").getStateForCompleteSequence().isFinal());
        Assert.assertTrue(triggerWords.get("word2").getStateForCompleteSequence().isFinal());

    }

    @Test
    public void testThatTriggerWordsAreTurnedToLowerCaseForFlagLowerCaseInputFalse() {
        final WordBreakCompoundRewriterFactory factory = new WordBreakCompoundRewriterFactory("w2", () -> null,
                "field1", false, 1, 2, 1, Arrays.asList("Word1", "word2"), false, 2, false);

        final TrieMap<Boolean> triggerWords = factory.getReverseCompoundTriggerWords();
        Assert.assertFalse(triggerWords.get("word1").getStateForCompleteSequence().isFinal());
        Assert.assertTrue(triggerWords.get("Word1").getStateForCompleteSequence().isFinal());
        Assert.assertTrue(triggerWords.get("word2").getStateForCompleteSequence().isFinal());

    }
}
