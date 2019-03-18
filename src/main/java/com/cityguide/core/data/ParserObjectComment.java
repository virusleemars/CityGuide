package com.cityguide.core.data;

public class ParserObjectComment {
    enum CommentObjectData {IDCITY, DATE, TIME, REVIEW, RATING}

    private ParserObject parserObject;

    public ParserObjectComment(ParserObject parserObject) {
        this.parserObject = parserObject;
    }

    private String getData(CommentObjectData comment){
        return new ParserLine(parserObject.getObjectData().get(comment.ordinal() +
                              parserObject.getSizeHeader())).getData();
    }

    public Integer getIDCity(){
        return Integer.parseInt(getData( CommentObjectData.IDCITY));
    }

    public String getDate(){
        return getData( CommentObjectData.DATE);
    }

    public String getTime(){
        return getData( CommentObjectData.TIME);
    }

    public String getReview(){
        return getData( CommentObjectData.REVIEW);
    }

    public Byte getRating(){
        return Byte.parseByte(getData(CommentObjectData.RATING));
    }
}

