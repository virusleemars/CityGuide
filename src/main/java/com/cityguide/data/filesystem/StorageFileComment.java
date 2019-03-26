package com.cityguide.data.filesystem;

import com.cityguide.core.Comment;
import com.cityguide.core.Storing;
import java.util.*;

public class StorageFileComment implements Storing<Comment> {
    private static final String RESOURCE_COMMENT_FILE = "resource/comment.txt";

    private ParserObjectFile<Comment> parserObjectFile = new ParserObjectFile<>(RESOURCE_COMMENT_FILE);

    @Override
    public List<Comment> readAll() {
        List<Comment> list = new ArrayList<>();
        parserObjectFile.loadParseObjects( ParserObjectComment::new );
        for ( ParserObject<Comment> objectComment: parserObjectFile.getList()){
            Comment comment = objectComment.getEntity();
            list.add(comment);
        }
        return list;
    }

    @Override
    public void writeAll(List<Comment> list) {
        parserObjectFile.clear();
        for (Comment comment : list){
            ParserObjectComment parserObjectComment = new ParserObjectComment();
            parserObjectComment.setEntity(comment);
            parserObjectFile.add(parserObjectComment);
        }
        parserObjectFile.saveParserObjects();
    }

    @Override
    public Comment read(int index) {
        ParserObject<Comment> parserObjectComment = parserObjectFile.get(index);
        return parserObjectComment.getEntity();
    }

    @Override
    public void write(Comment element) {
        ParserObjectComment parserObjectComment = new ParserObjectComment();
        parserObjectComment.setEntity(element);
        parserObjectFile.add(parserObjectComment);
        parserObjectFile.saveParserObjects();
    }

    @Override
    public Comment remove(int index) {
        ParserObject<Comment> parserObjectComment = parserObjectFile.remove(index);
        return parserObjectComment.getEntity();
    }
}
