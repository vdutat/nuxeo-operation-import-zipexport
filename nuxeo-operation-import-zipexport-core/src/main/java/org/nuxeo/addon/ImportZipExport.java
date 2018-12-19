package org.nuxeo.addon;

import java.io.IOException;

import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.platform.filemanager.service.extension.ExportedZipImporter;

/**
 * curl -XPOST -u Administrator:Administrator "http://localhost:8080/nuxeo/api/v1/path/default-domain/workspaces/ws1/export.zip/@blob/file:content/@op/FileManager.ImportZipExport" -H "Content-type:application/json" -d "{\"params\":{\"path\":\"/default-domain/workspaces/ws1\"}}"
 */
@Operation(id=ImportZipExport.ID, category=Constants.CAT_DOCUMENT, label="FileManager.ImportZipExport", description="Describe here what your operation does.")
public class ImportZipExport {

    public static final String ID = "FileManager.ImportZipExport";

    @Context
    protected CoreSession session;

    @Param(name = "path", required = true)
    protected String path;

    @OperationMethod
    public DocumentModel run(Blob input) throws IOException {
        ExportedZipImporter importer = new ExportedZipImporter();
        return importer.create(session, input, path, false, null, null);
    }
}
