# nuxeo-operation-import-zipexport

## About / Synopsis

This plugin demonstrates how to ...

It was generated with the following commands:
```
mkdir nuxeo-operation-import-zipexport && cd $_
nuxeo bootstrap multi-module operation
# Edit operation's java class
nuxeo bootstrap package
mvn package
```

## Requirements

Building requires the following software:

* git
* maven

## Build

```
git clone ...
cd nuxeo-operation-import-zipexport

mvn clean install
```

## Installation

```
nuxeoctl mp-install nuxeo-operation-import-zipexport/nuxeo-operation-import-zipexport-package/target/nuxeo-operation-import-zipexport-*.zip
```

## How to use

### ZIP export of folderish document

In a browser, navigate to [http://localhost:8080/nuxeo/api/v1/path/default-domain/workspaces/ws1/@rendition/zipTreeExport](http://localhost:8080/nuxeo/api/v1/path/default-domain/workspaces/ws1/@rendition/zipTreeExport)


### Create File document for ZIP export

```
curl -su Administrator:Administrator -XPOST \
-H "properties:*" -H 'Content-Type:application/json' \
http://localhost:8080/nuxeo/api/v1/repo/default/path/default-domain/workspaces/ws2 \
-D /tmp/response-headers.txt \
-d '{ "entity-type":"document", "name":"ws1-export.zip","type":"File", "properties": { "dc:title":"ws1-export.zip"} }' \
|jq
```

### Attach ZIP export to File document

```
curl -su Administrator:Administrator -XPOST \
http://localhost:8080/nuxeo/api/v1/automation/Blob.AttachOnDocument \
-F request="{\"params\":{\"document\":\"/default-domain/workspaces/ws2/ws1-export.zip\"},\"context\":{}}" \
-F input=@ws1-export.zip \
> /dev/null
```

### Import ZIP export

```
curl -XPOST -vu Administrator:Administrator \
-H "Content-type:application/json" -H'properties:*' \
http://localhost:8080/nuxeo/api/v1/path/default-domain/workspaces/ws2/ws1-export.zip/@blob/file:content/@op/FileManager.ImportZipExport \
-d '{
  "params":{
    "path":"/default-domain/workspaces/ws2"
  },
  "context": {}
}'
```
==> Workspace `/default-domain/workspaces/ws2/ws1` gets created.

## Support

**These features are not part of the Nuxeo Production platform, they are not supported**

These solutions are provided for inspiration and we encourage customers to use them as code samples and learning resources.

This is a moving project (no API maintenance, no deprecation process, etc.) If any of these solutions are found to be useful for the Nuxeo Platform in general, they will be integrated directly into platform, not maintained here.


## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## About Nuxeo

Nuxeo Platform is an open source Content Services platform, written in Java. Data can be stored in both SQL & NoSQL databases.

The development of the Nuxeo Platform is mostly done by Nuxeo employees with an open development model.

The source code, documentation, roadmap, issue tracker, testing, benchmarks are all public.

Typically, Nuxeo users build different types of information management solutions for [document management](https://www.nuxeo.com/solutions/document-management/), [case management](https://www.nuxeo.com/solutions/case-management/), and [digital asset management](https://www.nuxeo.com/solutions/dam-digital-asset-management/), use cases. It uses schema-flexible metadata & content models that allows content to be repurposed to fulfill future use cases.

More information is available at [www.nuxeo.com](https://www.nuxeo.com).

