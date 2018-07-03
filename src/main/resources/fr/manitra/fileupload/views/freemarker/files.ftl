<#-- @ftlvariable name="" type="fr.manitra.fileupload.views.FilesView" -->
<#include "header.html">

<#setting number_format="##0">

<div class="container">
	<div class="card">
	  <div class="card-header">
	    Files
	  </div>
	  <div class="card-body">
	    <#if fileInfos?size != 0>
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">File name</th>
			      <th scope="col">Size (Byte)</th>
			      <th scope="col">Upload date</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<#list fileInfos as fileInfo>
				    <tr>
				      <th scope="row"><input type="checkbox" name="${fileInfo.fileName}"/></th>
				      <td>${fileInfo.fileName}</td>
				      <td>${fileInfo.size}</td>
				      <td>${fileInfo.uploadDate?datetime?string('dd-MM-yyyy HH:mm')}</td>
				    </tr>
			    </#list>
			  </tbody>
			</table>
		<#else>
			<h4 class="card-title">No file found at backup directory!</h4>
		</#if>
	    
	  </div>
	</div>
	
	<#include "uploadform.html">
	
</div>

<#include "footer.html">
<#include "js.html">
