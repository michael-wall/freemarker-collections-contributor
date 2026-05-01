## Information Template snippet using journalArticleAssetEntryHelper ##
- Requires TemplateContextContributor and JournalArticleAssetEntryHelper.
```
<#assign assetEntryId = journalArticleAssetEntryHelper.getAssetEntryId(request)!0>
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
- Requires TemplateContextContributor and JournalArticleAssetEntryHelper.
```
<#assign assetEntryId = journalArticleAssetEntryHelper.getAssetEntryId(request)!0>
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
- Requires TemplateContextContributor and JournalArticleAssetEntryHelper.
- Doesn't use assetEntryLocalService or journalArticleLocalService from TemplateContextContributor.
```
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService")>
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")>
<#assign assetEntryId = journalArticleAssetEntryHelper.getAssetEntryId(request)!0>
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

## Information Template snippet using serviceLocator (not recommended) and request.getAttribute (not recommended). ##
- Does not require TemplateContextContributor OR JournalArticleAssetEntryHelper.
- System Settings > Template Engines > FreeMarker Engine.
	- Requires 'org.apache.catalina.core.ApplicationHttpRequest' to be an Allowed class to call request.getAttribute.
	- Requires serviceLocator to be allowed.
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