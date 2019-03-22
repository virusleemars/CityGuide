package com.cityguide.core.data;

public class ParserObjectComment extends ParserObject {
    enum CommentObjectData {IDCITY, DATE, TIME, REVIEW, RATING}

    @Override
    String getData(Integer offset){
        return new ParserLine( getObjectData().get(offset + getHeaderSize())).getData();
    }

    public Integer getIDCity(){
        return Integer.parseInt(getData( CommentObjectData.IDCITY.ordinal()));
    }

    public String getDate(){
        return getData( CommentObjectData.DATE.ordinal());
    }

    public String getTime(){
        return getData( CommentObjectData.TIME.ordinal());
    }

    public String getReview(){
        return getData( CommentObjectData.REVIEW.ordinal());
    }

    public Byte getRating(){
        return Byte.parseByte(getData(CommentObjectData.RATING.ordinal()));
    }
}

