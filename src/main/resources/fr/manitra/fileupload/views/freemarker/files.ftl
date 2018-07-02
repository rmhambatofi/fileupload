<#-- @ftlvariable name="" type="fr.manitra.fileupload.views.FilesView" -->
<#include "header.html">

<#setting number_format=",##0">

<div class="container">
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
	
	<form>
		<div class="input-group">
		  <div class="custom-file">
		    <input type="file" class="custom-file-input" id="InputFile">
		    <label class="custom-file-label" for="InputFile">Choose file</label>
		  </div>
		  <div class="input-group-append">
		    <button class="btn btn-outline-secondary" type="button">Upload</button>
		  </div>
		</div>
	</form>
</div>

<#include "footer.html">
