<#-- @ftlvariable name="" type="fr.manitra.fileupload.views.FilesView" -->
<html>
    <body>        
        <h1>Upload & Download files tool</h1>
        <ul>
	        <#list fileInfos as fileInfo>
	        	 <li>${fileInfo.fileName}</li>
	        </#list>
        </ul>
    </body>
</html>