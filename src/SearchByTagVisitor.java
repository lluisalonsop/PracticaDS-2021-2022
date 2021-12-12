import java.util.Objects;

public class SearchByTagVisitor implements Visitor {
  private final String tag;

  public SearchByTagVisitor(String tagToSet) {
    this.tag = tagToSet;
  }

  @Override
  public Node visitNode(Node p) {
    for (int i = 0; i < p.tags.size(); i++) {
      if (Objects.equals(p.tags.get(i), tag)) {
        return p;
      }
    }
    return new Project("null", 0);
  }
}
