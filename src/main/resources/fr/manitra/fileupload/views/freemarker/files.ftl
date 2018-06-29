<#-- @ftlvariable name="" type="fr.manitra.fileupload.views.FilesView" -->
<#include "header.html">

<ul>
    <#list fileInfos as fileInfo>
    	 <li>${fileInfo.fileName}</li>
    </#list>
</ul>

<button class="foo-button mdc-button">Button</button>

<#include "footer.html">
