package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class DictionaryManagement {
    public DictionaryManagement() {
    }

    public void insertFromCommandline(Dictionary lis, String tu, String nghia) {
        Word a = new Word(tu, nghia);
        lis.words.add(a);
    }

    public void insertFromFile(Dictionary lis) throws FileNotFoundException {
        File file = new File("D:\\LTHDT\\Dictionary2.0\\src\\sample\\dictionaries.txt");
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()) {
            String a = sc.nextLine();
            if (a.length() >= 1) {
                String[] arr = a.split(":");
                Word w = new Word(arr[0], arr[1]);
                lis.words.add(w);
            }
        }

        sc.close();
    }

    public Word get_word(Dictionary lis, String a) {
        for(int i = 0; i < lis.words.size(); ++i) {
            if (a.equalsIgnoreCase(((Word)lis.words.get(i)).getWord_target())) {
                return (Word)lis.words.get(i);
            }
        }

        Word exception = new Word();
        exception.setWord_target("lỗi");
        exception.setWord_explain("");
        return exception;
    }

    public int dictionaryRemove(Dictionary lis, String remove_word) {
        for(int i = 0; i < lis.words.size(); ++i) {
            if (remove_word.equalsIgnoreCase(((Word)lis.words.get(i)).getWord_target())) {
                lis.words.remove(i);
                return 1;
            }
        }

        return 0;
    }

    public void dictionaryFix(Word word_fix, String target, String explain) {
        word_fix.setWord_target(target);
        word_fix.setWord_explain(explain);
    }

    public void save_file(Dictionary lis) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("D:\\LTHDT\\Dictionary2.0\\src\\sample\\dictionaries.txt", "UTF-8");
        writer.print("");

        for(int i = 0; i < lis.words.size(); ++i) {
            String var10001 = ((Word)lis.words.get(i)).getWord_target();
            writer.append(var10001 + ":" + ((Word)lis.words.get(i)).getWord_explain() + "\n");
        }

        writer.close();
    }
}
