import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String firstWord = "dog";
        String lastWord = "cat";
        String[] wordList = {"dol","dot","dat","ase","wsa","don"};
        Set<String> wordSet = new HashSet(Arrays.asList(wordList));
        WordChain wordChain = new WordChain(new ArrayList(),wordSet,firstWord);

        List<WordChain> answerList = new ArrayList();
        answerList.add(wordChain);
        for (int i = 0; i < wordSet.size(); i++){
            answerList = loop(answerList,lastWord);
        }
        for (WordChain chain : answerList){
            if (chain.isFinished()){
                chain.getChain().forEach(p-> System.out.print(p + " | "));
                System.out.println();
            }
        }
    }

    public static List<WordChain> loop(List<WordChain> list, String endWord){
        List<WordChain> newList = new ArrayList();
        for (WordChain wordChain : list){
            if (isChainFinished(wordChain, endWord)) {
                newList.add(wordChain);
            }
            newList.addAll(generateCombination(wordChain));
        }
        return newList;
    }


    public static List<WordChain> generateCombination(WordChain wordChain){
        List<WordChain> list = new ArrayList();
        for (String word : wordChain.getAvaliableWords()){
            if (!wordChain.isFinished() && isMachs(wordChain.getLastWord(), word)){
                list.add(new WordChain(wordChain.getChain(),wordChain.avaliableWords,word));
            }
        }
        return list;
    }

    static class WordChain {
        List<String> chain;
        Set<String> avaliableWords;
        String lastWord;
        boolean finished;

        public WordChain(List<String> chain, Set<String> availableWordsSet, String word){
            this.chain = new ArrayList(chain);
            this.avaliableWords = new HashSet(availableWordsSet);
            this.chain.add(word);
            if (chain.size() > 0) {
                this.avaliableWords.remove(word);
            }
            lastWord = word;
        }

        public List<String> getChain() {
            return chain;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public Set<String> getAvaliableWords() {
            return avaliableWords;
        }

        public String getLastWord() {
            return lastWord;
        }
    }

    public static boolean isChainFinished(WordChain wordChain, String endWord){
        if (wordChain.isFinished()) { return true;}
        if (isMachs(wordChain.getLastWord(), endWord)) {
            wordChain.setFinished(true);
            wordChain.getChain().add(endWord);
            return true;
        }
        return false;
    }

     public static boolean isMachs(String str1, String str2){
        if (str1.length() != str2.length()) {
            return false;
        }
            char[] arr1 = str1.toCharArray();
            char[] arr2 = str2.toCharArray();

        int matcherCount = 0;
        for (int i =0; i < arr1.length; i++){
            if (arr1[i] != arr2[i]){
                matcherCount++;
            }
        }
        return matcherCount <= 1;
    }
}
