package com.mycompany.spring2.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAO {

    private final String BOARD_INSERT = "insert into BOARD (photo, category, title, writer, content) values (?,?,?,?,?)";
    private final String BOARD_UPDATE = "update BOARD set photo=?, category=?, title=? writer=? content=? where seq=?";
    private final String BOARD_DELETE = "delete from BOARD  where seq=?";
    private final String BOARD_GET = "select * from BOARD  where seq=?";
    private final String BOARD_LIST = "select * from BOARD order by seq desc";


    @Autowired
    private JdbcTemplate template;

    public int insertBoard(BoardVO vo) {
        return template.update(BOARD_INSERT, new
                Object[]{vo.getPhoto(), vo.getCategory(), vo.getTitle(), vo.getWriter(), vo.getContent()});
                //photo, category, title, writer, content
    }

    public int deleteBoard(int seq) {
        return template.update(BOARD_DELETE,
                new Object[]{seq});
    }

    public int updateBoard(BoardVO vo) {
        return template.update(BOARD_UPDATE,
                new Object[]{vo.getPhoto(), vo.getCategory(), vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getSeq()});
                //update BOARD set photo=?, category=?, title=? writer=? content=? where seq=?"
    }

    public BoardVO getBoard(int seq) {
        return template.queryForObject(BOARD_GET,
                new Object[] {seq},
                new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
    }

    public List<BoardVO> getBoardList() {
        return template.query(BOARD_LIST, new RowMapper<BoardVO>(){

        @Override
        public BoardVO mapRow(ResultSet rs, int rowNum) throws  SQLException {
                BoardVO data = new BoardVO();
                data.setSeq(rs.getInt("seq"));
                data.setPhoto(rs.getString("photo"));
                data.setCategory(rs.getString("category"));
                data.setTitle(rs.getString("title"));
                data.setWriter(rs.getString("writer"));
                data.setContent(rs.getString("content"));
                data.setRegdate(rs.getDate("regdate"));
                return data;
            }
        });
    }
}


