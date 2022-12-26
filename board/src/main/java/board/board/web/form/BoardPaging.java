package board.board.web.form;

import lombok.Data;
import lombok.Getter;
// 변수의 네이밍은 중요
@Data
public class BoardPaging {
    private Integer pageSize; // board의 전체 갯수
    private Integer currentPage; // 현재 페이지 offset 5 * currentPage
    private Integer startPage;
    private Integer endPage;
    public static final Integer RANGE = 5; // 하단 페이지
    public static final Integer BOARD_SIZE= 5;

    public BoardPaging() {

    }

    public BoardPaging(Integer pageSize, Integer currentPage) {
        this.pageSize = (pageSize / RANGE) + 1;
        this.currentPage = currentPage;
        int middleOfRange = RANGE / 2;
        setRangeValue(currentPage, middleOfRange);
    }

    private void setRangeValue(Integer currentPage, int middleOfRange) {
        if (pageSize < RANGE) {
            startPage = 1;
            endPage = pageSize;
        }
        else if ((RANGE - currentPage) < middleOfRange) { // n-2, n-1, n, n+1, n+2
            int temporalEndPage = currentPage + middleOfRange;
            startPage = currentPage - middleOfRange;
            this.endPage = temporalEndPage < pageSize ? temporalEndPage : pageSize;
        } else { // 1, 2, 3, 4, 5
            startPage = 1;
            endPage = RANGE;
        }
    }
}
