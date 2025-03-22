package games.enchanted.verticalslabs.item.creative_tab.modifier;

public enum CreativeTabInsertionPosition {
    BEFORE(true),
    AFTER(true),
    FIRST(),
    LAST();

    public final boolean requiresExistingItem;

    CreativeTabInsertionPosition() {
        this.requiresExistingItem = false;
    }
    CreativeTabInsertionPosition(boolean requiresExistingItem) {
        this.requiresExistingItem = requiresExistingItem;
    }
}
