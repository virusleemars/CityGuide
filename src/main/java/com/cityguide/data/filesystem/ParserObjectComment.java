package com.cityguide.data.filesystem;

import com.cityguide.core.Comment;

public class ParserObjectComment extends ParserObject<Comment> {
    enum CommentObjectData {IDCITY, DATE, REVIEW, RATING}

    private String getData(Integer offset){
        return new ParserLine( getObjectData().get(offset + getHeaderSize())).getData();
    }

    private void setData(CommentObjectData commentObjectData, String data){
        getObjectData().set( commentObjectData.ordinal() + getHeaderSize(),
                ParserLine.convert( commentObjectData.name(), data));
    }

    public void doEmpty(){
        super.doEmpty();
        for (CommentObjectData commentObjectData: CommentObjectData.values()){
            writeString( ParserLine.convert( commentObjectData.name(), ""));
        }
    }

    private Integer getIDCity(){
        return Integer.parseInt(getData( CommentObjectData.IDCITY.ordinal()));
    }

    private void setIDCity(Integer id){
        setData(CommentObjectData.IDCITY, String.valueOf(id));
    }

    private String getDate(){
        return getData( CommentObjectData.DATE.ordinal());
    }

    private void setDate(String date){
        setData( CommentObjectData.DATE, date);
    }

    private String getReview(){
        return getData( CommentObjectData.REVIEW.ordinal());
    }

    private void setReview(String review){
        setData(CommentObjectData.REVIEW, review);
    }

    private void setRating(int rating){
        setData(CommentObjectData.RATING, String.valueOf(rating));
    }


    private Integer getRating(){
        return Integer.parseInt(getData(CommentObjectData.RATING.ordinal()));
    }

    @Override
    public Comment getEntity() {
        Comment comment = new Comment();
        comment.setName(super.getMyName());
        comment.setId(super.getMyID());
        comment.setIdParent(super.getMyIDParent());
        comment.setRating(this.getRating());
        comment.setCalendar(this.getDate());
        comment.setReview(this.getReview());
        comment.setIdCity(this.getIDCity());
        return comment;
    }

    @Override
    public void setEntity(Comment entity) {
        this.doEmpty();
        super.setMyID(entity.getId());
        super.setMyName(entity.getName());
        super.setMyIDParent(entity.getIdParent());
        this.setIDCity(entity.getIdCity());
        this.setRating(entity.getRating());
        this.setReview(entity.getReview());
        this.setDate(entity.getCalendar());
    }
}

