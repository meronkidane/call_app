sub init()
    m.rowList = m.top.FindNode("rowList")
    m.rowList.rowFocusAnimationStyle = "floatingFocus"
    mockRows = [
        {
            title: "Trending",
            children: [
                { title: "Sample Movie", id: "movie-1" },
                { title: "Sample Series", id: "series-1" }
            ]
        }
    ]
    m.rowList.content = mockRows
end sub
