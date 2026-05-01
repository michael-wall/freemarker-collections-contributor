## Information Template snippet using journalArticleAssetEntryHelper ##
- Makes use of the custom TemplateContextContributor.
```
<#assign infoItemReference = request.getAttribute("INFO_ITEM_REFERENCE")!{}>	
<#assign assetEntryId = infoItemReference.getInfoItemIdentifier().getClassPK()!0>
<#assign journalArticle = journalArticleAssetEntryHelper.getJouralArticleByAssetEntryId(assetEntryId)!>

<#if journalArticle?? && journalArticle?has_content>
    <@liferay_journal["journal-article"]
        articleId=journalArticle.getArticleId()
        ddmTemplateKey='6244802'
        groupId=scopeGroupId
    />
</#if>

```

## Information Template snippet using assetEntryLocalService and journalArticleLocalService ##
- Makes use of the custom TemplateContextContributor.
```
<#assign infoItemReference = request.getAttribute("INFO_ITEM_REFERENCE")!{}>	
<#assign assetEntryId = infoItemReference.getInfoItemIdentifier().getClassPK()!0>
<#assign assetEntry = assetEntryLocalService.fetchAssetEntry(assetEntryId)!>

<#if assetEntry?? 
    && assetEntry?has_content
    && assetEntry.getClassName()?lower_case == "com.liferay.journal.model.journalarticle">

    <#assign journalArticle = journalArticleLocalService.fetchLatestArticle(assetEntry.getClassPK())!>

    <#if journalArticle?? && journalArticle?has_content>
        <@liferay_journal["journal-article"]
            articleId=journalArticle.getArticleId()
            ddmTemplateKey='6244802'
            groupId=scopeGroupId
        />
    </#if>
</#if>
```

## Information Template snippet using serviceLocator (not recommended) ##
- Does not make use of the custom TemplateContextContributor.
```
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService")>
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")>
<#assign infoItemReference = request.getAttribute("INFO_ITEM_REFERENCE")!{}>	
<#assign assetEntryId = infoItemReference.getInfoItemIdentifier().getClassPK()!0>
<#assign assetEntry = assetEntryLocalService.fetchAssetEntry(assetEntryId)!>

<#if assetEntry?? 
    && assetEntry?has_content
    && assetEntry.getClassName()?lower_case == "com.liferay.journal.model.journalarticle">

    <#assign journalArticle = journalArticleLocalService.fetchLatestArticle(assetEntry.getClassPK())!>

    <#if journalArticle?? && journalArticle?has_content>
        <@liferay_journal["journal-article"]
            articleId=journalArticle.getArticleId()
            ddmTemplateKey='6244802'
            groupId=scopeGroupId
        />
    </#if>
</#if>
```
