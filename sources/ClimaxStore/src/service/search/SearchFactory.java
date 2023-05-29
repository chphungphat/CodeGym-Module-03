package service.search;

public class SearchFactory {
    private static final SearchFactory searchFactory = new SearchFactory();

    private SearchFactory() {}

    public static SearchFactory getInstance() {
        return searchFactory;
    }

    private final String NAME = "name";
    private final String PIRCE = "price";
    private final String DEVELOPER = "developer";
    private final String GAMETAGS = "gametags";

    public Search getSearch(String searchBy) {
        Search newSearch = SearchByName.getInstance();
        switch (searchBy) {
            case NAME -> {
                return SearchByName.getInstance();
            }
            case PIRCE -> {
                return SearchByPrice.getInstance();
            }
            case GAMETAGS -> {
                return SearchByGameTag.getInstance();
            }
            case DEVELOPER -> {
                return SearchByDeveloper.getInstance();
            }
            default -> {
                System.err.println("Error");
            }
        }
        return newSearch;
    }
}
