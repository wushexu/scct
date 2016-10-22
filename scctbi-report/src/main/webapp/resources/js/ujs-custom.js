$(document).on('ajax:success', 'a[data-remote],form[data-remote]', function(evt, data, status, xhr){
    var guessScript = /^\s*(\$|alert|var |function)/.test(data)
    if(guessScript) return false
    var $trigger=$(this)
    var then_toggle=$trigger.attr('data-toggle')
    $([['update','html'],['replace','replaceWith'],['append'],['prepend'],['before'],['after']]).each(function(i,e){
        var containerId=$trigger.attr('data-'+e[0])
        var manipulate=e[1]||e[0]
        if(containerId!=null){
            var $container=(containerId=='')? $trigger : $('#'+containerId)
            $container[manipulate](data)
        }
        return false
    })
})
