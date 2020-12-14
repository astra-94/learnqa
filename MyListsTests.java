package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testSaveTwoArticlesToMyList()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String first_search_line = "Java";
        SearchPageObject.typeSearchLine(first_search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        String second_search_line = "Appium";
        SearchPageObject.typeSearchLine(second_search_line);
        SearchPageObject.clickByArticleWithTitle(second_search_line);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMyListNotFirstTime(name_of_folder);
        ArticlePageObject.closeArticle();


        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);
        //убеждаемся, что вторая осталась
        MyListPageObject.waitForArticleToAppearByTitle(second_search_line);
        SearchPageObject.clickByArticleWithTitle(second_search_line);

        //переход в статью и проверка тайтла
        String article_second_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title!",
                second_search_line,
                article_second_title
        );

    }
}
