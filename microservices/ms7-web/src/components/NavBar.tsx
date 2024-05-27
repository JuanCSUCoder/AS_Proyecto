import HomeIcon from "@mui/icons-material/Home";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { ListItemDecorator, Tab, TabList, TabPanel, Tabs } from "@mui/joy";
import Link from "next/link";
import { ReactNode } from "react";

export function NavBar() {
  return (
    <div className="flex flex-row items-center justify-center">
      <Tabs
        size="lg"
        orientation="horizontal"
        className="items-center justify-center bg-white m-0 mb-4"
      >
        <TabList disableUnderline>
          <Link href="/">
            <Tab variant="soft" disableIndicator>
              <ListItemDecorator>
                <HomeIcon />
              </ListItemDecorator>
              Productos
            </Tab>
          </Link>
          <Link href="/cart">
            <Tab variant="soft" disableIndicator>
              <ListItemDecorator>
                <ShoppingCartIcon />
              </ListItemDecorator>
              Carrito
            </Tab>
          </Link>
        </TabList>
      </Tabs>
    </div>
  );
}