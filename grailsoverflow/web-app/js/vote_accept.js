$("#vote a").click(function() {
    var shouldSelect = true;

    if (this.classList.contains('selected'))
        shouldSelect = false;

    if ($(this).parent().attr('id') == "answerTick") {
        if (this.classList.contains('allowed')) {
            $("body").find("#answerTick a").each(function(){
                $(this).removeClass('selected');
            });
        } else {
            shouldSelect = false;
        }
    } else {
        $(this).parent().find('a').each(function(){
            if ($(this).parent().attr('id') != "answerTick")
                $(this).removeClass('selected');
        });
    }

    if (shouldSelect)
        $(this).addClass('selected');
});