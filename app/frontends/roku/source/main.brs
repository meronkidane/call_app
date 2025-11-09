sub RunUserInterface()
    screen = CreateObject("roSGScreen")
    m.global = screen.GetGlobalNode()
    port = CreateObject("roMessagePort")
    screen.SetMessagePort(port)

    scene = screen.CreateScene("MainScene")
    screen.Show()

    while true
        msg = wait(0, port)
        if type(msg) = "roSGScreenEvent" and msg.isScreenClosed() then
            return
        end if
    end while
end sub
