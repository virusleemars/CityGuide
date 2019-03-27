package com.cityguide.data.database;

import com.cityguide.core.Comment;
import com.cityguide.core.Storing;
import java.sql.*;
import java.util.List;

public class StorageDBComment extends StorageDB<Comment> implements Storing<Comment>{
    private static final String DATA_BASE_TABLE = "COMMENT";

    enum TableCommentCol{id, name, id_parent, id_city, rating, calendar, review}

    public StorageDBComment() {
        super(DATA_BASE_TABLE);
    }

    void createTable(){
        String var =
                TableCommentCol.id.name() + " INT PRIMARY KEY     NOT NULL," +
                        TableCommentCol.id_parent.name() + " INT," +
                        TableCommentCol.name.name() + " TEXT," +
                        TableCommentCol.id_city.name() + " INT," +
                        TableCommentCol.review.name() + " TEXT," +
                        TableCommentCol.rating.name() + " TEXT," +
                        TableCommentCol.calendar.name() + " TEXT";
        super.createTable(var);
    }

    @Override
    public List<Comment> readAll() {
        return super.readAll(this::createComment);
    }

    private Comment createComment(ResultSet resultSet)  {
        Comment comment = null;
        try {
            comment = new Comment();
            comment.setName(resultSet.getString(TableCommentCol.name.name()));
            comment.setId(resultSet.getInt(TableCommentCol.id.name()));
            comment.setIdParent(resultSet.getInt(TableCommentCol.id_parent.name()));
            comment.setRating(resultSet.getInt(TableCommentCol.rating.name()));
            comment.setReview(resultSet.getString(TableCommentCol.review.name()));
            comment.setCalendar(resultSet.getString(TableCommentCol.calendar.name()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    private String createSqlByPlace(Comment comment){
        String id = String.valueOf(comment.getId());
        String id_parent = String.valueOf(comment.getIdParent());
        String idCity = String.valueOf(comment.getIdCity());
        String rating = String.valueOf(comment.getRating());
        return  TableCommentCol.id.name() +", " +
                TableCommentCol.name.name() + ", " +
                TableCommentCol.id_parent.name() + ", " +
                TableCommentCol.id_city + ", " +
                TableCommentCol.rating.name() +", " +
                TableCommentCol.review.name() + ", " +
                TableCommentCol.calendar.name() + ") " +
                "VALUES (" + id +
                ", '" + comment.getName() +
                "', " + id_parent +
                ", " + idCity +
                ", " + rating +
                ", '" + comment.getReview() +
                "', '" + comment.getCalendar() + "'";
    }

    @Override
    synchronized public void writeAll(List<Comment> list) {
        super.writeAll(list, this::createSqlByPlace);
    }
}
