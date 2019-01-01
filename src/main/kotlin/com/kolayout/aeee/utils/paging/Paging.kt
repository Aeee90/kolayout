package com.kolayout.aeee.utils.paging

class Paging {

    private var rowSize: Int = 0
        get() = rowSize

    private var count: Int
        get() = count

    private var pageNO: Int = 0
        get() = pageNO

    private var pageCount: Int = 0
        get() = pageCount

    private var pageStart: Int = 0
        get() = pageStart

    private var pageEnd: Int = 0
        get() = pageEnd

    private var rowStart: Int = 0
        get() = rowStart

    private var rowEnd: Int = 0
        get() = rowEnd

    private val PAGE_BUTTON_SIZE = 4

    constructor(count: Int, rowSize: Int = 0, pageNO: Int = 1){
        val total = if (count <= 0) 0 else count
        if (rowSize <= 0) { //rowSize all로 계산
            this.rowStart = 1
            this.pageEnd = this.rowStart
            this.pageStart = this.pageEnd
            this.pageCount = this.pageStart
            this.pageNO = this.pageCount
            this.rowEnd = total
        } else {

            this.pageCount = total / rowSize + if (total % rowSize > 0) 1 else 0
            this.pageNO = if (pageNO > this.pageCount) this.pageCount else pageNO

            pageEnd = pageNO
            pageStart = pageEnd
            var temp = PAGE_BUTTON_SIZE
            while (pageStart > 1 && temp > PAGE_BUTTON_SIZE / 2) {
                pageStart--
                temp--
            }
            while (pageEnd < pageCount && temp > 0) {
                pageEnd++
                temp--
            }
            while (pageStart > 1 && temp > 0) {
                pageStart--
                temp--
            }

            this.rowStart = if (pageNO <= 1) 1 else rowSize * pageNO - rowSize + 1
            this.rowEnd = rowSize * pageNO

            this.rowSize = rowSize
        }
        this.count = total
    }
}