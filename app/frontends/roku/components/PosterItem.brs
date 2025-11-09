sub init()
    m.titleLabel = m.top.FindNode("title")
end sub

sub itemContentChanged()
    m.titleLabel.text = m.top.itemContent.title
end sub
