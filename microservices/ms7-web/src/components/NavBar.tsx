import HomeIcon from "@mui/icons-material/Home";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import ViewStreamIcon from "@mui/icons-material/ViewStream";
import { ListItemDecorator, Tab, TabList, TabPanel, Tabs } from "@mui/joy";
import Link from "next/link";
import { ReactNode } from "react";

export function NavBar() {
  return (
    <div className="flex flex-row items-center justify-center">
      <Tabs
        size="md"
        orientation="horizontal"
        className="items-center justify-center m-0 mb-4"
        sx={{
          backgroundColor: "transparent"
        }}
      >
        <TabList disableUnderline sx={{
          overflow: 'hidden',
          borderRadius: 12
        }}>
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
          <Link href="/orders">
            <Tab variant="soft" disableIndicator>
              <ListItemDecorator>
                <ViewStreamIcon />
              </ListItemDecorator>
              Pedidos
            </Tab>
          </Link>
        </TabList>
      </Tabs>
    </div>
  );
}