$(function(){
    var sampleTags = ['c', 'ant', 'c++', 'java', 'php', 'c#', 'groovy', 'jquery', 'grails', 'javascript', 'asp', 'ruby', 'python', 'scala', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];

    //-------------------------------
    // Single field
    //-------------------------------
    $('#tagit_singleFieldTags').tagit({
        availableTags: sampleTags,
        // This will make Tag-it submit a single form value, as a comma-delimited field.
        singleField: true,
        singleFieldNode: $('#tagit_singleFieldTags_value')
    });
});