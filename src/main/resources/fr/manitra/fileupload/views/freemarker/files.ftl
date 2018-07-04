<#-- @ftlvariable name="" type="fr.manitra.fileupload.views.FilesView" -->
<#include "header.html">

<#include "js.html">

<#setting number_format="##0">

<div class="container">
	<div class="card">
	  <div class="card-header">
	    Files
	  </div>
	  <div class="card-body">
	    <#if fileInfos?size != 0>
	    	<form id="fileListForm" method="POST">
	    		<input type="hidden" id="FileNameTxt" name="FileNameTxt"/>
				<table class="table table-striped">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">File name</th>
				      <th scope="col">Size (Byte)</th>
				      <th scope="col">Upload date</th>
				      <th scope="col"></th>
				      <th scope="col"></th>
				    </tr>
				  </thead>
				  <tbody>
				  	<#list fileInfos as fileInfo>
					    <tr>
					      <th scope="row"><input type="checkbox" name="SelectedNames" onchange="checkSelectedName(this, '${fileInfo.fileName}');"/></th>
					      <td>${fileInfo.fileName}</td>
					      <td>${fileInfo.size}</td>
					      <td>${fileInfo.uploadDate?datetime?string('dd-MM-yyyy HH:mm')}</td>
					      <td><a id="downloadLink" href="#" onclick="downloadFile('${fileInfo.fileName}');"><i class="fas fa-download"></i></a></td>
					      <td><a id="removeLink" href="#" onclick="removeFile('${fileInfo.fileName}');"><i class="far fa-trash-alt"></i></a></td>
					    </tr>
				    </#list>
				  </tbody>
				</table>
			</form>
		<#else>
			<h4 class="card-title">No file found at backup directory!</h4>
		</#if>
	    
	  </div>
	</div>
	<br>
	<#include "uploadform.html">
	
</div>

<#include "footer.html">

